package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zzacd;
    /* access modifiers changed from: private */
    public Bundle zzace;
    /* access modifiers changed from: private */
    public LinkedList<C0011zza> zzacf;
    private final zzf<T> zzacg = new zzf<T>() {
        public void zza(T t) {
            LifecycleDelegate unused = zza.this.zzacd = t;
            Iterator it = zza.this.zzacf.iterator();
            while (it.hasNext()) {
                ((C0011zza) it.next()).zzb(zza.this.zzacd);
            }
            zza.this.zzacf.clear();
            Bundle unused2 = zza.this.zzace = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.zza$zza  reason: collision with other inner class name */
    private interface C0011zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, C0011zza zza) {
        if (this.zzacd != null) {
            zza.zzb(this.zzacd);
            return;
        }
        if (this.zzacf == null) {
            this.zzacf = new LinkedList<>();
        }
        this.zzacf.add(zza);
        if (bundle != null) {
            if (this.zzace == null) {
                this.zzace = (Bundle) bundle.clone();
            } else {
                this.zzace.putAll(bundle);
            }
        }
        zza(this.zzacg);
    }

    public static void zzb(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String zzh = zzf.zzh(context, isGooglePlayServicesAvailable);
        String zzi = zzf.zzi(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzh);
        linearLayout.addView(textView);
        if (zzi != null) {
            Button button = new Button(context);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzi);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    context.startActivity(GooglePlayServicesUtil.zzar(isGooglePlayServicesAvailable));
                }
            });
        }
    }

    private void zzdu(int i) {
        while (!this.zzacf.isEmpty() && this.zzacf.getLast().getState() >= i) {
            this.zzacf.removeLast();
        }
    }

    public void onCreate(final Bundle savedInstanceState) {
        zza(savedInstanceState, (C0011zza) new C0011zza() {
            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzacd.onCreate(savedInstanceState);
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FrameLayout frameLayout = new FrameLayout(inflater.getContext());
        final LayoutInflater layoutInflater = inflater;
        final ViewGroup viewGroup = container;
        final Bundle bundle = savedInstanceState;
        zza(savedInstanceState, (C0011zza) new C0011zza() {
            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.zzacd.onCreateView(layoutInflater, viewGroup, bundle));
            }
        });
        if (this.zzacd == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.zzacd != null) {
            this.zzacd.onDestroy();
        } else {
            zzdu(1);
        }
    }

    public void onDestroyView() {
        if (this.zzacd != null) {
            this.zzacd.onDestroyView();
        } else {
            zzdu(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle attrs, final Bundle savedInstanceState) {
        zza(savedInstanceState, (C0011zza) new C0011zza() {
            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzacd.onInflate(activity, attrs, savedInstanceState);
            }
        });
    }

    public void onLowMemory() {
        if (this.zzacd != null) {
            this.zzacd.onLowMemory();
        }
    }

    public void onPause() {
        if (this.zzacd != null) {
            this.zzacd.onPause();
        } else {
            zzdu(5);
        }
    }

    public void onResume() {
        zza((Bundle) null, (C0011zza) new C0011zza() {
            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzacd.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.zzacd != null) {
            this.zzacd.onSaveInstanceState(outState);
        } else if (this.zzace != null) {
            outState.putAll(this.zzace);
        }
    }

    public void onStart() {
        zza((Bundle) null, (C0011zza) new C0011zza() {
            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzacd.onStart();
            }
        });
    }

    public void onStop() {
        if (this.zzacd != null) {
            this.zzacd.onStop();
        } else {
            zzdu(4);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzf<T> zzf);

    public T zzou() {
        return this.zzacd;
    }
}
